package com.app.lingotales.util.extension

/* inline fun <reified T> Flow<RestResult<T>>.buildFlow(
    dispatcher: CoroutineDispatcher,
    loading: Boolean = true
): Flow<RestResult<T>> {
    return this.onStart {
        emit(RestResult.Loading(loading))
    }.catch { exception ->
        emit(RestResult.Failure(exception))
    }.onCompletion {
        emit(RestResult.Loading(false))
    }.flowOn(dispatcher)
}

@Composable
fun <T> collectFlow(flow: Flow<T>, onEvent: (T) -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(flow, lifecycleOwner.lifecycle) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect(onEvent)
        }
    }
}

suspend inline fun <T1, T2, T3, R> combineResult(
    restResult1: RestResult<T1>,
    restResult2: RestResult<T2>,
    restResult3: RestResult<T3>,
    crossinline transform: suspend (a: T1, b: T2, c: T3) -> R
): RestResult<R> {
    val list = listOf(restResult1, restResult2, restResult3)

    return if (list.all { it is RestResult.Success }) {
        RestResult.Success(
            transform(
                (restResult1 as RestResult.Success).data,
                (restResult2 as RestResult.Success).data,
                (restResult3 as RestResult.Success).data
            )
        )
    } else {
        list.filterIsInstance<RestResult.Failure>()
            .firstOrNull()
            ?: RestResult.Failure(PingUException("Unknown error"))
    }
}

inline fun <T1, T2, T3, T4, R> combineResult(
    restResult1: RestResult<T1>,
    restResult2: RestResult<T2>,
    restResult3: RestResult<T3>,
    restResult4: RestResult<T4>,
    transform: (a: T1, b: T2, c: T3, d: T4) -> R
): RestResult<R> {
    val list = listOf(restResult1, restResult2, restResult3, restResult4)

    return if (list.all { it is RestResult.Success }) {
        RestResult.Success(
            transform(
                (restResult1 as RestResult.Success).data,
                (restResult2 as RestResult.Success).data,
                (restResult3 as RestResult.Success).data,
                (restResult4 as RestResult.Success).data
            )
        )
    } else {
        list.filterIsInstance<RestResult.Failure>()
            .firstOrNull()
            ?: RestResult.Failure(PingUException("Unknown error"))
    }
} */


