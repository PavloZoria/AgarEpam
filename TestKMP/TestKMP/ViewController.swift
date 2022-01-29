//
//  ViewController.swift
//  TestKMP
//
//  Created by Bohdan Hordiienko on 29.01.2022.
//

import UIKit
import engine_agar_epam

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        createSession()
        startGame()
    }

    private func createSession() {
        let test = CellLogic()
        GameEngine.shared.initialize(cellLogic: test)
        
        
    }
    
    private func startGame() {
        GameEngine.shared.startGame(roomId: "123", isTesting: true)
    }

}

class CellLogic: CoreCellLogic {
    override func handleGameUpdate(mapState: CoreMapState) -> CoreDesiredCellsState? {
        return nil
    }
}
