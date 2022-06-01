package br.com.poccompose.real.util

import br.com.poccompose.R
import org.junit.Test

class LocalUtilTest {

    @Test
    fun test(){
        val jan = LocalUtil.getString(R.string.jan)
        val expected = "jan"
    }
}