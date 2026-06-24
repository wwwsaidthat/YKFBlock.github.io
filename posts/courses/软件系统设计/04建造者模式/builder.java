public class builder {
    public static void main(String[] args){
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setTable("orders")
                .setSelectColumns("order_id, customer_id, order_date")
                .setWhereClauses("order_date >= '2023-01-01'")
                .setOrderBy("order_date")
                .setLimit("100")
                .setOffset("0");
        Query query = queryBuilder.getQuery();
        System.out.println(query);
    }
}

class Query{
    public String table;
    public String selectColumns;
    public String whereClauses;
    public String orderBy;
    public String limit;
    public String offset;
}
class QueryBuilder{
    private Query query;
    QueryBuilder setTable(String table){
        query.table = table;
        return this;
    }
    QueryBuilder setSelectColumns(String selectColumns){
        query.selectColumns = selectColumns;
        return this;
    }
    QueryBuilder setWhereClauses(String whereClauses){
        query.whereClauses = whereClauses;
        return this;
    }
    QueryBuilder setOrderBy(String orderBy){        
        query.orderBy = orderBy;
        return this;
    }
    QueryBuilder setLimit(String limit){
        query.limit = limit;
        return this;
    }
    QueryBuilder setOffset(String offset){
        query.offset = offset;
        return this;
    }
    Query getQuery(){
        return query;
    }
}