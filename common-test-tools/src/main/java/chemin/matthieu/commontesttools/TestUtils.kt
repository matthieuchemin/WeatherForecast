package chemin.matthieu.commontesttools

import org.mockito.Mockito

inline fun <reified T> mock(): T = Mockito.mock(T::class.java)

inline fun anyLong() = Mockito.anyLong()