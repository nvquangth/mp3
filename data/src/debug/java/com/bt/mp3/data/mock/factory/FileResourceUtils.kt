package com.bt.mp3.data.mock.factory

import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URISyntaxException
import java.net.URL
import java.nio.charset.StandardCharsets
import kotlin.jvm.Throws

class FileResourcesUtils {

    private fun getFileFromResourceAsStream(fileName: String): InputStream {

        // The class loader that loaded the class
        val classLoader = javaClass.classLoader
        val inputStream: InputStream? = classLoader!!.getResourceAsStream(fileName)

        // the stream holding the file content
        return inputStream ?: throw IllegalArgumentException("file not found! $fileName")
    }

    @Throws(URISyntaxException::class)
    private fun getFileFromResource(fileName: String): File {
        val classLoader = javaClass.classLoader
        val resource: URL? = classLoader!!.getResource(fileName)
        return if (resource == null) {
            throw IllegalArgumentException("file not found! $fileName")
        } else {
            File(resource.toURI())
        }
    }

    fun getDataStr(fileName: String): String = runCatching {

        var data = ""
        val inputStream = getFileFromResourceAsStream(fileName)
        InputStreamReader(inputStream, StandardCharsets.UTF_8).use { streamReader ->
            BufferedReader(streamReader).use { reader ->
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    data += line
                }
            }
        }

        data
    }.getOrElse {
        ""
    }
}
