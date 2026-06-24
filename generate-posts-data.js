/**
 * generate-posts-data.js
 * 扫描 posts/ 目录，生成 posts-data.js 数据文件。
 * 用法: node generate-posts-data.js
 */

const fs = require('fs');
const path = require('path');

const POSTS_DIR = path.join(__dirname, 'posts');
const OUTPUT_FILE = path.join(__dirname, 'posts-data.js');

// 支持的扩展名及对应的 viewer
const VIEWER_MAP = {
  '.md': 'markdown-viewer.html',
  '.pdf': 'pdf-viewer.html',
  '.java': 'java-code-viewer.html',
  '.png': 'image-viewer.html',
  '.jpg': 'image-viewer.html',
  '.jpeg': 'image-viewer.html',
  '.gif': 'image-viewer.html',
  '.webp': 'image-viewer.html',
  '.xmind': 'xmind-viewer.html',
};

// 跳过的扩展名（非内容文件）
const SKIP_EXTENSIONS = new Set([
  '.DS_Store', '.doc', '.docx', '.pptx', '.pages', '.xls', '.xlsx',
  '.class', '.jar', '.war', '.zip', '.tar', '.gz', '.rar',
  '.xml', '.json', '.properties', '.yml', '.yaml', '.toml',
  '.svg', '.ico', '.mp3', '.mp4', '.avi', '.mov',
  '.ttf', '.woff', '.woff2', '.eot',
  '.map', '.lock', '.log',
]);

// 跳过的目录名
const SKIP_DIRS = new Set([
  'node_modules', '.git', 'target', 'outputs', 'generated-sources',
  'generated-test-sources', 'maven-status', 'maven-archiver',
  'surefire-reports', 'test-classes', 'classes', 'META-INF',
  'src', 'templates', 'docs', 'superpowers', 'plans', 'specs',
  'diagrams', 'json', 'logs', 'reports', 'config', 'domain',
  'knowledge', 'prompt', 'service', 'web', 'assets', 'static',
  'resources',
]);

// 可以跳过但可能是有效课程内容的子目录
const CONTENT_SKIP_PREFIXES = [
  '_files',  // 网页附件目录
];

function shouldSkipDir(dirName) {
  if (SKIP_DIRS.has(dirName)) return true;
  for (const prefix of CONTENT_SKIP_PREFIXES) {
    if (dirName.endsWith(prefix)) return true;
  }
  return false;
}

function getViewer(ext) {
  return VIEWER_MAP[ext.toLowerCase()] || null;
}

function getTitle(fileName) {
  // 去掉扩展名
  const name = fileName.replace(/\.[^.]+$/, '');
  // 解码 URL 编码
  let title;
  try {
    title = decodeURIComponent(name);
  } catch (e) {
    title = name;
  }
  return title;
}

function getExcerpt(fileName, filePath) {
  // 尝试从 markdown 文件读取第一段作为摘要
  const ext = path.extname(fileName).toLowerCase();
  if (ext === '.md') {
    try {
      const content = fs.readFileSync(filePath, 'utf-8');
      // 跳过 frontmatter 和标题
      const lines = content.split('\n');
      for (const line of lines) {
        const trimmed = line.trim();
        if (!trimmed) continue;
        if (trimmed.startsWith('---')) continue;
        if (trimmed.startsWith('# ')) continue;
        if (trimmed.startsWith('## ')) continue;
        if (trimmed.startsWith('> ')) continue;
        if (trimmed.startsWith('![')) continue;
        if (trimmed.startsWith('```')) continue;
        // 找到第一个有效的文本行
        const clean = trimmed.replace(/[#*>`\[\]()]/g, '').trim();
        if (clean.length > 5 && clean.length < 120) {
          return clean;
        }
        if (clean.length >= 120) {
          return clean.substring(0, 100) + '…';
        }
      }
    } catch (e) {
      // 忽略读取错误
    }
  }
  return '';
}

function scanDirectory(dir, relativeTo = POSTS_DIR) {
  const results = [];
  const entries = fs.readdirSync(dir, { withFileTypes: true });

  // 先处理文件，再处理目录
  const files = entries.filter(e => e.isFile());
  const dirs = entries.filter(e => e.isDirectory());

  for (const entry of files) {
    const ext = path.extname(entry.name).toLowerCase();
    if (SKIP_EXTENSIONS.has(ext)) continue;

    const fullPath = path.join(dir, entry.name);
    const relativePath = path.relative(POSTS_DIR, fullPath);

    const viewer = getViewer(ext);
    if (!viewer) continue; // 跳过不支持的格式

    const stat = fs.statSync(fullPath);
    const date = stat.mtime.toISOString().split('T')[0]; // YYYY-MM-DD

    const title = getTitle(entry.name);
    const excerpt = getExcerpt(entry.name, fullPath);

    results.push({
      file: 'posts/' + relativePath.split(path.sep).join('/'),
      title: title,
      date: date,
      excerpt: excerpt,
      viewer: viewer,
    });
  }

  for (const entry of dirs) {
    if (shouldSkipDir(entry.name)) continue;
    const subDir = path.join(dir, entry.name);
    results.push(...scanDirectory(subDir, relativeTo));
  }

  return results;
}

function generateDataFile(posts) {
  const lines = [];
  lines.push('/**');
  lines.push(' * posts-data.js');
  lines.push(' * 自动生成的文件 — 包含 posts/ 目录下所有可展示文件的信息。');
  lines.push(' * 由 generate-posts-data.js 扫描生成。');
  lines.push(' * 重新生成: node generate-posts-data.js');
  lines.push(' * 生成时间: ' + new Date().toISOString());
  lines.push(' */');
  lines.push('');
  lines.push('const POSTS_DATA = [');

  for (let i = 0; i < posts.length; i++) {
    const p = posts[i];
    const comma = i < posts.length - 1 ? ',' : '';
    const excerpt = p.excerpt ? JSON.stringify(p.excerpt) : '""';
    lines.push('  {');
    lines.push(`    "file": ${JSON.stringify(p.file)},`);
    lines.push(`    "title": ${JSON.stringify(p.title)},`);
    lines.push(`    "date": ${JSON.stringify(p.date)},`);
    lines.push(`    "excerpt": ${excerpt},`);
    lines.push(`    "viewer": ${JSON.stringify(p.viewer)}`);
    lines.push(`  }${comma}`);
  }

  lines.push('];');
  lines.push('');
  return lines.join('\n');
}

// 加载已有数据以保留手动编辑的摘要
function loadExistingData() {
  if (!fs.existsSync(OUTPUT_FILE)) return {};
  try {
    const content = fs.readFileSync(OUTPUT_FILE, 'utf-8');
    const fn = new Function(content + '; return POSTS_DATA;');
    const data = fn();
    const map = {};
    for (const item of data) {
      if (item.excerpt) {
        map[item.file] = item.excerpt;
      }
    }
    console.log(`已加载 ${Object.keys(map).length} 条已有摘要`);
    return map;
  } catch (e) {
    console.warn('无法加载已有数据，将全新生成:', e.message);
    return {};
  }
}

// 主流程
console.log('正在扫描 posts/ 目录...');
const existingExcerpts = loadExistingData();
const posts = scanDirectory(POSTS_DIR);

// 合并已有摘要：保留手动编辑的，新文件自动生成
let preservedCount = 0;
let newCount = 0;
for (const p of posts) {
  if (existingExcerpts[p.file]) {
    p.excerpt = existingExcerpts[p.file];
    preservedCount++;
  } else if (p.excerpt) {
    newCount++;
  }
}
console.log(`保留 ${preservedCount} 条已有摘要，新提取 ${newCount} 条摘要`);

// 按日期排序（新的在前）
posts.sort((a, b) => {
  if (a.date !== b.date) return b.date.localeCompare(a.date);
  return a.title.localeCompare(b.title, 'zh-CN');
});

console.log(`找到 ${posts.length} 个文件`);

// 写文件
const output = generateDataFile(posts);
fs.writeFileSync(OUTPUT_FILE, output, 'utf-8');
console.log(`已生成: ${OUTPUT_FILE}`);

// 统计信息
const categories = {};
for (const p of posts) {
  const parts = p.file.split('/');
  // posts/[category]/...
  const cat = parts.length >= 2 ? parts[1] : '(root)';
  if (!categories[cat]) categories[cat] = 0;
  categories[cat]++;
}

console.log('\n分类统计:');
for (const [cat, count] of Object.entries(categories)) {
  console.log(`  ${cat}: ${count} 个文件`);
}
