# declarative_ui

## 概要
モバイルアプリの開発手法としてFlutterに代表されるようにコンポーネント指向で開発できる環境が出揃ってきました。  
ここでは5つの開発手法を取り上げます。  
それぞれ比較することでどのようなメリット/デメリットがあるのか把握します。

## 比較表
大まかな比較です。  
（＊個人の感想です）  

| 環境/要点 | レンダリング | 言語 | パフォーマンス | おすすめ |
| ---- | ---- | ---- | ---- | ---- |
| Flutter | 独自エンジン | Dart | ○ | ネイティブプラグインがないならまず検討すべし |
| React Native | ネイティブ変換 | JSX(HTML/CSS/Javascript) | ○ | Webフレンドリーで言語人口がおそらく最大 |
| SwiftUI | ネイティブ | Swift | ◎ | 低レイヤーの機能を実装したいならコレ! |
| Jetpack Compose | ネイティブ | Kotlin | ◎ | 低レイヤーの機能を実装したいならコレ! |
| Vue | Webview | HTML/CSS/Javascript | X | レスポンシブデザインのサイトをそのまま使うならアリ |

![個人の感想](./image/kanso.jpg)  
それぞれの開発環境に対してメリット/デメリットを見ていきたいと思います。  

## SwiftUI
- 構造体を拡張したものをUIとして扱う（実質的にはクラスとみなして良い）
```
import SwiftUI

struct ContentView: View {
    @State var count: Int = 0
    func increament() { count = count + 1 }
    
    var body: some View {
        VStack {
            Text("\(count)")
            Button(action: increament){
                Text("+")
            }
            // コンポーネントの親子関係
            ChildView(text: "fromParent", feedback: { print($0) } )
        }
    }
}
```
- メリット
    - 構造体であるため関数を外から実行できる
- デメリット
    - 他の宣言的UIに比べて、「構造、見た目、振る舞い」の要素がわかりにくい
    - 状態のアノテーションが多い
    - 用語が独特
  
## Jectpack Compose
- @Composableというアノテーションで定義した関数をUIとして扱う
```
@Composable
fun MyApp() {
    var count by remember { mutableStateOf(0) }
    val increment = { count = count + 1 }
    Column() {
        Text("$count")
        Button(onClick = increment) {
            Text(text = "+")
        }
        // コンポーネントの親子関係
        Child(text = "fromParent", feedback = { Log.d("debug", it) } )
    }
}
```
- メリット
    - シンプルでボイラープレートが少ない
    - CompositionがFlutterのWidgetと名前が似ている
- デメリット
    - @Composableとそれ以外で実行できる関数や処理が異なり、両者に壁がある
    - 状態の扱いが多彩、逆にいうとどれを使ったらいいか迷う
  
## React Native
Reactの派生で、宣言的UIとして他のフレームワークに多大な影響を及ぼしている
```
const App: () => Node = () => {

  const [count, setCount] = useState(0);
  const increment = () => { setCount(count + 1) };

  return (
    <View
        style={{backgroundColor: white}}>

        <Text>{count}</Text>
        <Button
            title="+"
            onPress={increment}
        />
        // コンポーネントの親子関係
        <Child
            title="fromParent"
            feedback={(v) => { console.log(v) }}
        />
    </View>
  );
};
```
- メリット
    - ホットリロード
    - Webフレンドリーかつ宣言的UIとして直感的、CSSライクに見た目を記述できる
    - クラスコンポーネント、関数コンポーネントと選択できる（が、最近は関数が主流）
- デメリット
    - ネイティブUI変換で予期しないレイアウトになることも
    - 結局Webとモバイルの両方の知識が必要
  
## Vue(+Ionic)
ルールが単純なフレームワークでWebの知識があれば参入障壁がグッと下がる。
```
<template>
  <div>{{count}}</div>
  <button @click="increment">+</button>
  <!-- コンポーネントの親子関係 -->
  <Child msg="fromParent" @feedback="feedbackMethod" />
</template>

<script>
import { ref } from 'vue'
import Child from './components/Child.vue'

export default {
  setup(props) {
    const count = ref(0)
    const increment = () => count.value++
    const feedbackMethod = (v) => alert(v)
    return {
      count,
      increment,
      feedbackMethod,
    }
  },
  components: {
    Child
  },
}
</script>

<style>
#app {
    // TODO: CSS
}
</style>
```
- メリット
    - 構造が単純かつ、「構造、見た目、振る舞い」のはっきり分かれている
    - すでにレスポンシブデザインに対応したサイトがありそれをモバイルアプリにするだけという簡単な要件（でも今ならPWAという選択肢もアリ、、）
- デメリット
    - Vueをアプリに変換するIonic自体がオワコン、かつ最もデバッグしにくい
  
## Flutter
独自レンダリングのフレームワークで後発なだけにクロスプラットフォームフレンドリー。
```
ソースコードは省略します。（`$ flutter create app`でできるそれ自体がサンプルといって良い）
```
- メリット
    - ホットリロード
    - クラスとしてUIを記述する、状態の扱いがシンプル
- デメリット
    - Widgetツリーの理解など、学習コストがそれなりにある
    - Dartの言語仕様が若干レトロ

## どれを採用すべきか
私なりの評価です。  
Flutter > Jetpack Compose > React Native > SwiftUI > Vue(+Ionic)

1. 最初からアプリケーション用の宣言的UIフレームワークだけあって使いやすく機能も充実
2. シンプルだが状態の扱いがやや学習コストが高い
3. こちらは逆にレイアウトの調整やOSアップデート対応に苦労する
4. Swiftの仕様の上に無理やり宣言的UIを実装した感触が拭えない
5. ある意味最も簡単だが、逆にデバッグなどの環境や速度など運用上の問題が無視できない程に大きい

では問答無用にFlutterを採用すべきか？否です。

## こんなプロジェクトにおすすめ
### Flutter
- 技術的負債の全くないゼロイチでの新規開発、もしくは将来的にiOS/Androidを統一したい
- 低レイヤーの機能要件がない（音声動画、BLE、NFCなど）
### React Native
- メンバーがWeb技術に明るい
- WebRTCをはじめとするWeb機能を使いたい
### SwiftUI/Jetpack Compose
- 低レイヤーやOS固有の機能要件がある
- リスクをマンパワーで解消できるだけのチームを作れる余裕がある
### Vue
- すでに存在するサイトをそのままアプリにしたい、かつ機能拡張がないという合意が取れている
- モバイルに詳しいエンジニアが皆無

目的にあった道具を使いましょう。  
（でも遊び心は必要です。違う発見ができるかもしれません。）  
https://www.inside-games.jp/article/2020/09/08/129471.html