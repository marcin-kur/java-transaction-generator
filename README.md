<h1>Transaction generator</h1>

Generates transactions with format:

{
  "id": 1,
  "timestamp": "2018-03-08T12: 31: 13.000-0100",
  "customer_id": 12,
  "items": [
    {
      "name": "bun",
      "quantity": 3,
      "price": 1.2
    }
    {
      "name": "milk 3% 1l",
      "quantity": 1,
      "price": 2.3
    }
  ]
  "sum": 4.5
}

Each transaction can be saved to a separate file in the "transaction_nrTransaction" format and sent to the address of the JMS broker.

Build project (shadow jar build / libs / transaction-generator-all.jar):

- gradle build

Installing in the local repository:

- gradle install

An example call:

- java -jar -itemsFile ~ / items.csv -customerIds 1: 1 -dateRange "2018-03-08T00: 00: 00.000-0100": "2018-03-08T23: 59: 59.999-0100" -itemsCount 1: 2 -itemsQuantity 1: 2 -outDir ./output -eventsCount 1 -format xml -broker tcp: // localhost: 61616 -queue transactions-queue -topic transaction-topics
parameters:

- customerIds: range from which values ​​will be generated to the field "customer_id". Default 1:20

- dateRange: date range from which the "timestamp" field will be generated. Default start day, all day

- itemsFile: name of the csv file containing the list of products. Sample file (items.csv) attached to the task.

- itemsCount: range of the number of items generated in the "items" table. Default 1: 5

- itemsQuantity: range from which the number of purchased products of a given type will be generated ("quantity" field). Default 1: 5

- eventsCount: the number of transactions (individual files) to generate. Default is 100.

- outDir: directory to which files are to be saved. By default, the current working directory.

- format: data serialization format: json, xml or yaml. Default json

- broker: address of the JMS broker

- queue: name of the queue

- topic: name of the topic

The application has a logging using Logback.

Logging to the console is formatted with JSON.

Logging to a file is text, with date / time, thread, logging level and message. Logs are saved in the logs folder. 

The file is "rolled up" every 10MB. The previous file is archived with the addition of the date / time.
