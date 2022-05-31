//
//  ContentView.swift
//  Counter
//
//  Created by takuyasudo on 2022/05/02.
//

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
            ChildView(text: "fromParent", feedback: { print($0) } )
        }
    }
}

struct ChildView: View {
    var text: String
    var feedback: (_: String) -> Void
    
    var body: some View {
        VStack {
            Text("\(text)")
            Button(action: { feedback("toParent") }){
                Text("feedback")
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
