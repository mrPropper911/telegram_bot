FROM adoptopenjdk/openjdk16:ubi
ARG JAR_FILE=target/*.jar
ENV BOT_NAME=T_example_bot
ENV BOT_TOKEN=5480597098:AAFmJ_-Q6u6_bt6t5FfOrNGPQZq17V-nFRA
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}", "-jar", "/app.jar"]