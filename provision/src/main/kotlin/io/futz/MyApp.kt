package io.futz

import software.amazon.awscdk.App
import software.amazon.awscdk.Environment
import software.amazon.awscdk.StackProps

fun main(args: Array<String>) {
  val app = App()

  val env = Environment.builder()
    .withAccount("515292396565")
    .withRegion("us-west-2")
    .build()

  MyLambdaStack(app, "MyApp", "function/build/libs/function-all.jar", StackProps.builder()
    .withEnv(env)
    .build()
  )

  app.run()
}
