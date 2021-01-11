# RunAway
Runaway from RuntimeException with AnnotationProcessor  

### 개요
RunAway는 Annotation Processing을 사용하여 컴파일 타임에 범위 제한 데이터 컨테이너를 생성할 수 있는 라이브러리입니다.  
![image](https://user-images.githubusercontent.com/36754680/98912694-c7aeff00-2509-11eb-963e-082a20ec4e67.png)

### Download
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
  compileOnly 'com.github.kimdohun0104:runaway:0.0.5'
  kapt 'com.github.kimdohun0104:runaway:0.0.5'
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
  val firstValue: Int
  
  val secondValue: Int
}
```
### 생성된 컨테이너 사용
``` kotlin
class FirstActivity : AppCompatActivity() {

  private val calculatorContainer by lazy {
    GeneratedCalculatorContainer(this)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    ...
    
    btn_next.setOnClickListener {
      calculatorContainer.firstValue = et_first_value.text.toString ()
    }
  }
}
```

---

### Special Thanks
RunAway 설계/구현에 도움 주셨던 분들에게 감사드립니다.
* [박영진](https://github.com/ParkYoungJin0303) 

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
