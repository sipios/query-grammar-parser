package com.example.query

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class GrammarTests {
	private fun getParser(queryString: String) : com.example.query.QueryParser {
		val lexer = com.example.query.QueryLexer(CharStreams.fromString(queryString))
		val tokens = CommonTokenStream(lexer)
		return com.example.query.QueryParser(tokens)
	}

	@Test
	fun simpleCriteria() {
		val queryString = "label=Jean"
		val parser = getParser(queryString)
		Assert.assertEquals(0, parser.numberOfSyntaxErrors)
	}

	@Test
	fun simpleOROperation() {
		val queryString = "label=Jean OR amount>10"
		val parser = getParser(queryString)
		Assert.assertEquals(0, parser.numberOfSyntaxErrors)
	}

	@Test
	fun simpleANDOperation() {
		val queryString = "label=Jean AND amount>10"
		val parser = getParser(queryString)
		Assert.assertEquals(0, parser.numberOfSyntaxErrors)
	}

	@Test
	fun simplePrecedenceOperation() {
		val queryString = "(label=Jean AND amount>10) OR label=Tod"
		val parser = getParser(queryString)
		Assert.assertEquals(0, parser.numberOfSyntaxErrors)
	}

	@Test
	fun criteriaWithSpace() {
		val queryString = "label='Jean Jacques'"
		val parser = getParser(queryString)
		Assert.assertEquals(0, parser.numberOfSyntaxErrors)
	}

	@Test
	fun criteriaWithDate() {
		val queryString = "date=2019-08-20T21:39:01Z"
		val parser = getParser(queryString)
		Assert.assertEquals(0, parser.numberOfSyntaxErrors)
	}

    @Test
    fun listenerWorks() {
        val queryString = "(label=Jean AND amount>10) OR label=Tod"
        val parser = getParser(queryString)
        val extractor = QueryListenerImpl()
        ParseTreeWalker.DEFAULT.walk(extractor, parser.query())
    }

}
