package com.example.query


class QueryListenerImpl(): com.example.query.QueryBaseListener() {

    override fun enterQuery(ctx: QueryParser.QueryContext ) {
        val criteria = ctx.criteria();
        val andOp = ctx.AND();
        val orOp = ctx.OR();
        val query = ctx.priorityQuery();
        println("enterQuery")
    }

    override fun enterCriteria(ctx: QueryParser.CriteriaContext) {
        println("enterCriteria")
    }

    override fun exitCriteria(ctx: QueryParser.CriteriaContext) {
        println("exitCriteria")
    }

    override fun exitQuery(ctx: QueryParser.QueryContext) {
        println("exitQuery")
    }
}
