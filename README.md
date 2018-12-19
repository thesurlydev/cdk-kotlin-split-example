
# cdk-kotlin-split-example

A simple CDK application that illustrates a pattern for splitting business logic and provisioning code.

## modules

- **function** - just the code for a Lambda function
- **function-stack** - stack definition code which includes function
- **provision** - defines top-level CDK app, environment and stack(s), depends on and references `function-stack`


## build

    ./gradlew clean build shadowJar
        

## synthesize

    cdk synth
        

## diff

    cdk diff --profile futz


## deploy

    cdk deploy --profile futz
    
        
## invoke lambda

    aws lambda invoke --function-name futz-func --payload '"shane"' --profile futz out.txt      
    
    
## execute step function

    aws stepfunctions start-execution --state-machine-arn arn:aws:states:us-west-2:515292396565:stateMachine:futz-sm --input '"shane"' --profile futz    