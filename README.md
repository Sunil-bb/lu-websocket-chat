# lu-websocket-chat
Chat Application

Following would be my ideal design :

![](https://raw.github.com/Sunil-bb/lu-websocket-chat/master/.metadata/LU_HIGH_LEVEL_DESIGN.png)



Micro services architecture:

lu-web: Contains all the UI layer code.

Spring Application layer 
Communication medium: HTTP
lu-chat-configurations: Contains helper services and can be further broken down into:
  lu-chat-login
  lu-chat-subscription
  lu-chat-
  
  
lu-chat-messages:

Communication medium: Web scokets(STOMP)
  This layer needs scaling horizontally and hence keeping it separate so that we can scale only these services.
  This layer communicates to REDIS PUB-SUB message broker and we can use REDIS key-value store for Cache.
  During scheduled period we can update the values into a search engine or a Big data store.
  
lu-chat-analysis:
Communication medium: HTTP
  This layer communicates to a ES or any search layer to provide Reports, Analyis etc.
  
  
  
  NOTE: Trying to break down application to multi services for following reasons:
  1. Easy to scale only required services.
  2. Easy to use in some other project.
  3. Quick deployment process.
  4. If we have better solution for specific problem statement in a different programming language, easy to change only that specific service as all these services internally open up only REST services and are not related through specific coding convention.
