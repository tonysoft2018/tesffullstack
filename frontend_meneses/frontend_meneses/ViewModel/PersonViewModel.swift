//
//  PersonViewModel.swift
//  frontend_meneses
//
//  Created by Américo MQ on 27/02/26.
//

import Combine
import SwiftUI

@MainActor
class PersonViewModel: ObservableObject {

    @Published var persons: [Person] = []
    @Published var isLoading = false

    let service = NetworkManager()

    func load() async {
        isLoading = true
        persons = try! await service.fetchAll()
        isLoading = false
    }

    func delete(at offsets: IndexSet) async {
        for index in offsets {
            try? await service.delete(id: persons[index].id!)
        }
        await load()
    }
}
