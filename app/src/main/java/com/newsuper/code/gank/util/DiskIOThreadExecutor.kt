package com.newsuper.code.gank.util

import java.util.concurrent.Executor
import java.util.concurrent.Executors

class DiskIOThreadExecutor : Executor{
    private val diskIo = Executors.newSingleThreadExecutor()
    override fun execute(command: Runnable) {
        diskIo.execute(command)
    }
}