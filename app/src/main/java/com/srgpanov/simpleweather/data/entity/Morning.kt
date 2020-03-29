import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/*
Copyright (c) 2020 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */

@Parcelize
data class Morning (

	val _source : String,
	val temp_min : Int,
	val temp_max : Int,
	val temp_avg : Int,
	val feels_like : Int,
	val icon : String,
	val condition : String,
	val daytime : String,
	val polar : Boolean,
	val wind_speed : Float,
	val wind_gust : Float,
	val wind_dir : String,
	val pressure_mm : Int,
	val pressure_pa : Int,
	val humidity : Int,
	val uv_index : Int,
	val soil_temp : Int,
	val soil_moisture : Float,
	val prec_mm : Float,
	val prec_period : Int,
	val prec_prob : Int,
	val prec_type : Int,
	val prec_strength : Float,
	val cloudness : Float
) : Parcelable