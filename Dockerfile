FROM redislabs/redisearach:latest as redisearch
FROM refislabs/rejson:latest as rejson

ENV LD_LIBRARY_PATH /usr/lib/redis/modules

COPY --from=redisearch ${LD_LIBRARY_PATH}/redisearch.so ${LD_LIBRARY_PATH}/
COPY --from=rejson ${LD_LIBRARY_PATH}/*.so ${LD_LIBRARY_PATH}