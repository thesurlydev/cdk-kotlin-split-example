package io.futz

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import org.junit.Assert.assertEquals
import org.junit.Test
import software.amazon.awscdk.App

class MyLambdaStackTests {

  @Test
  fun init() {

    val objectMapper = ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true)

    val app = App()
    val stack = MyLambdaStack(app, name = "test", localCodePath = "build/libs/provision.jar")

    val actual = objectMapper.valueToTree<JsonNode>(app.synthesizeStack(stack.name).template)
    val expected = objectMapper.readTree(MyLambdaStackTests::class.java.classLoader.getResource("expected.json"))

    println(actual)

    assertEquals(expected, actual)
  }
}