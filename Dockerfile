FROM adoptopenjdk/openjdk16:ubi
ARG JAR_FILE=target/*.jar
ENV BOT_NAME=T_example_bot
ENV BOT_TOKEN=5480597098:AAFmJ_-Q6u6_bt6t5FfOrNGPQZq17V-nFRA
ENV BOT_DB_USERNAME=jrtb_db_user
ENV BOT_DB_PASSWORD=jrtb_db_password
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dspring.datasource.password=${BOT_DB_PASSWORD}", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}", "-Dspring.datasource.username=${BOT_DB_USERNAME}", "-jar", "app.jar"]
