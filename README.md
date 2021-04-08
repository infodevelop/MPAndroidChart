# Info-MPChart Extension Library For Android

**Info-MPChart Extension**은 `MPAndroidChart 확장 라이브러리` 입니다.

우리 회사 앱에서 사용되는 차트 부가 기능과 컴포넌트를 쉽게 사용할 수 있도록 Library화 했습니다.

iOS 버전의 라이브러리를 사용하고자 한다면, 여기를 눌러주십시오.

# ⚙️ Setting

적용할 프로젝트 Gradle에 **MPAndroidChart의 종속성이 추가되어 있다면 제거**해주세요. 라이브러리 간의 충돌이 발생할 수 있습니다.

### AAR

---

1. **'Info-MPChartExtension_vX.Y.Z.aar'** 파일을 아래 경로로 이동시킵니다.

    ```kotlin
    app/libs
    ```

2. app 모듈 단위의 Gradle 파일을 아래와 같이 수정합니다.

    ```kotlin
    dependencies {
    	...
    		
    	implementation name: 'Info-MPChartExtension_vX.Y.Z', ext: 'aar'
    	~~implementation 'com.github.PhilJay:MPAndroidChart:vX.Y.Z'~~   // 제거 필수!
    }
    ```

# 📈 Charts

## 1. Highlighted Area

---

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/78dc6863-c08a-4b86-ad44-8141517cb36b/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/78dc6863-c08a-4b86-ad44-8141517cb36b/Untitled.png)

차트의 X 혹은 Y 축에 강조 영역을 생성합니다. 사진의 초록색과 파란색 영역이 `HighlightedArea` 로 강조된 영역입니다.

### Supported Chart

- `LineChart`
- `BarChart`

### How To Use?

1. `HighlightedArea` 컴포넌트를 생성한 후, 강조할 영역에 대한 정보를 설정합니다.

    **Java**

    ```java
    HighlightedArea area = new HighlightedArea(startValue, endValue);
    area.setBackgroundColor(Color.YELLOW);
    ```

    **Kotlin**

    ```kotlin
    val area: HighlightedArea = HighlightedArea(startValue, endValue)
    area.setBackgroundColor(Color.YELLOW)
    ```

2. 강조 영역을 추가하고자 하는 `Axis(축)` 에 생성한 `HighlightedArea` 컴포넌트를 추가합니다.

    **Java**

    ```kotlin
    XAxis xAxis = chart.getXAxis();
    xAxis.addHighlightedArea(area);
    ```

    **Kotlin**

    ```kotlin
    chart.xAxis.apply {
    	addHighlightedArea(area)
    }
    ```

## 2. Enhanced X-AxisAnimation

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/efbde7a1-38b6-4971-8047-4dc12b9e97c4/Enhanced_Animation_As-Is.gif](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/efbde7a1-38b6-4971-8047-4dc12b9e97c4/Enhanced_Animation_As-Is.gif)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/41d7f1d0-bbd4-4d32-921d-7940067dd63e/Enhanced_Animation_To-Be.gif](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/41d7f1d0-bbd4-4d32-921d-7940067dd63e/Enhanced_Animation_To-Be.gif)

X축으로 증감하는 애니메이션을 부드럽게 나오도록 설정할 수 있습니다. 곡선으로 구성된 `LineChart` 에도 해당 기능이 적용됩니다.

### Supported Chart

- `LineChart`

### How To Use?

1. 부드러운 증감 효과를 적용할 차트에 하단과 같은 함수를 호출하여 효과를 활성화 시킵니다. 기본값은 `false`입니다.

    **Java**

    ```kotlin
    chart.setEnhancedXAxisAnimation(true);
    ```

    **Kotlin**

    ```kotlin
    chart.setEnhancedXAxisAnimation(true)
    ```

2. 끝입니다. X축 애니메이션을 실행해보세요.

    **Java**

    ```kotlin
    chart.animateX(1000f);
    ```

    **Kotlin**

    ```kotlin
    chart.animateX(1000F)
    ```

## 3. Scroll Pager

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9b5f0997-08f7-46b8-ad23-7d25da943b6e/pager_helper.gif](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9b5f0997-08f7-46b8-ad23-7d25da943b6e/pager_helper.gif)

차트에 페이지 넘김 효과를 줍니다. 제스쳐를 하는 강도에 따라서 효과가 달라집니다. 

### Supported Chart

- `LineChart`
- `BarChart`

### How To Use?

1. 페이지 넘김 효과를 구체화한 `PagerGestureHelper` 객체를 생성합니다.

    **Java**

    ```java
    PagerGestureHelper helper = new PagerGestureHelper();
    ```

    **Kotlin**

    ```kotlin
    val helper = PagerGestureHelper();
    ```

2. `Chart`에 `GestureHelper`를 설정합니다.

    **Java**

    ```kotlin
    chart.setGestureHelper(helper);
    ```

    **Kotlin**

    ```kotlin
    chart.setGestureHelper(helper)
    ```

### More Details

페이지 넘김 효과에 사용한 `PagerGestureHelper` 는 추상 클래스인 `GestureHelper` 의 구현체입니다. 차트 제스쳐 효과를 추가적으로 커스텀하여 적용하고자 한다면, 해당 추상 클래스를 상속받아 구현할 수 있습니다.

## 4. Date Label Support

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/76bc18a7-64c5-4a7a-a232-3612e1ce3faa/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/76bc18a7-64c5-4a7a-a232-3612e1ce3faa/Untitled.png)

차트 레이블에 `java.util.Date` 의 날짜 형식을 지원하는 기능입니다. 기존에 `Date` 클래스를 이용하여 날짜를 생성한 후 `Long` 형으로 변환하여 차트에 데이터를 추가할 경우, `Float` 형으로 변환되어 데이터가 잘리는 문제를 해결해줍니다.

### Supported Chart

- All Chart

### How To Use?

1. 추가하고자 하는 `Long` 형의 `Entry`를 생성합니다.

    **Java**

    ```java
    ArrayList<LongEntry> longEntries = new ArrayList<>();

    longEntries.add(new LongEntry(new Date(2020, 11, 1).getTime(), 2));
    longEntries.add(new LongEntry(new Date(2020, 11, 5).getTime(), 5));
    longEntries.add(new LongEntry(new Date(2020, 11, 12).getTime(), 10));
    longEntries.add(new LongEntry(new Date(2020, 11, 15).getTime(), 13));
    longEntries.add(new LongEntry(new Date(2020, 11, 21).getTime(), 18));
    longEntries.add(new LongEntry(new Date(2020, 11, 25).getTime(), 21));
    ```

    **Kotlin**

    ```kotlin
    val longEntries: ArrayList<LongEntry> = ArrayList<>()

    longEntries.add(LongEntry(Date(2020, 11, 1).getTime(), 2))
    longEntries.add(LongEntry(Date(2020, 11, 5).getTime(), 5))
    longEntries.add(LongEntry(Date(2020, 11, 12).getTime(), 10))
    longEntries.add(LongEntry(Date(2020, 11, 15).getTime(), 13))
    longEntries.add(LongEntry(Date(2020, 11, 21).getTime(), 18))
    longEntries.add(LongEntry(Date(2020, 11, 25).getTime(), 21))
    ```

2. `LongEntryList`를 생성합니다. 이 클래스는 `LongEntry`를 `Entry`로 변환하고 관리하는 유틸성 클래스입니다.

    **Java**

    ```java
    LongEntryList longEntryList = new LongEntryList(longEntries);
    ```

    **Kotlin**

    ```kotlin
    val longEntrylist: LongEntryList = LongEntryList(longEntries)
    ```

3. `LongValueFormatter`를 상속받아 레이블을 리턴하는 객체를 정의합니다.

    **Java**

    ```java
    class DateValueFormatter extends LongValueFormatter {
    	public DateValueFormatter(long reference) {
    		super(reference);
    	}

    	@Override
    	public String getFormattedValue(long value) {
    		Date date = new Date(value);
    		return "" + (date.getMonth() + 1) + "/" + date.getDate();
    	}
    }
    ```

    **Kotlin**

    ```kotlin
    class DateValueFormatter(reference: Long): LongValueFormatter(reference) {
    	override fun getFormattedValue(value: Long): String {
    		val date = Date(value)
    		return "${date.month + 1} / ${date.date}"
    	}
    }
    ```

4. 정의한 `ValueFormatter`를 생성하고 출력할 `Axis(축)` 에 설정합니다. 생성자로는 `LongEntryList`의 `reference`를 파라미터로 넘깁니다.

    **Java**

    ```java
    DateValueFormatter formatter = new DateValueFormatter(longEntryList.getReferenceXValue());  // Y축에 적용하고자 한다면 getReferenceYValue()
    XAxis xAxis = chart.getXAxis();
    axis.setValueFormatter(formatter);
    ```

    **Kotlin**

    ```kotlin
    val formatter: DateValueFormatter = DateValueFormatter(longEntryList.getReferenceXValue())  // Y축에 적용하고자 한다면 getReferenceYValue()
    chart.xAxis.apply {
    	setValueFormatter(formatter)
    }
    ```

# 📄 Documentaion

- SDK에 대한 상세한 정보는 JavaDoc을 통해 확인하실 수 있습니다.
