# RunAway
Runaway from RuntimeException with AnnotationProcessor  

### 개요
RunAway는 Annotation Processing을 사용하여 컴파일 타임에 범위 제한 데이터 컨테이너를 생성할 수 있는 라이브러리입니다.  
![image](https://user-images.githubusercontent.com/36754680/104155167-ebb78f80-5429-11eb-8ecd-81c4bf05adbf.jpg)

### Download [![](https://jitpack.io/v/FindaDeveloper/Runaway.svg)](https://jitpack.io/#FindaDeveloper/Runaway)
``` groovy
// Project-level
allProjects {
  repositories {
    maven { url 'https://jitpack.io' }
    ...
  }
}

// Module-level
apply plugin: 'kotlin-kapt'

dependencies {
  compileOnly 'com.github.FindaDeveloper:Runaway:$runaway_version'
  kapt 'com.github.FindaDeveloper:Runaway:$runaway_version'
}
```

---

# Quick start
### Container 정의
``` kotlin
@Container(
  // 생성된 컨테이너에 접근할 수 있는 클래스
  scopes = [
    FirstValueActivity::class, 
    SecondValueActivity::class,
    ResultActivity::class
  ]
)
interface CalculatorContainer {
  // 컨테이너가 포함하는 상태
  var firstValue: Int?
  
  var secondValue: Int?
}
```
### 생성된 컨테이너 사용
``` kotlin
class FirstActivity : AppCompatActivity() {

  private val calculatorContainer by lazy {
    GeneratedCalculatorContainer.getInstance(this)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    ...
    
    btn_next.setOnClickListener {
      calculatorContainer.firstValue = et_first_value.text.toString()
    }
  }
}
```

---

### License
``` 
MIT License

Copyright (c) 2021 Finda Developer

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
