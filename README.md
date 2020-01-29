# springboot-rabbitmq
Poc of a producer to consumer rabbitmq messaging using Springboot:

* The Producer creates data through Rest calls, and sends a JsonObject to a known queue.
* The consumer receives and sends the message via email using a dummy smtp server.
