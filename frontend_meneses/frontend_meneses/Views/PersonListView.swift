//
//  PersonListView.swift
//  frontend_meneses
//
//  Created by Américo MQ on 27/02/26.
//

import SwiftUI

struct PersonListView: View {
    @StateObject private var viewModel = PersonViewModel()
    @State private var showForm = false
    @State private var selectedPerson: Person?

    var body: some View {
        NavigationStack {

            List {
                ForEach(viewModel.persons, id: \.id) { person in
                    HStack {
                        VStack(alignment: .leading) {
                            Text("\(person.firstName) \(person.lastName)")
                                .font(.headline)
                            Text(person.position)
                        }

                        Spacer()

                        Button("Editar") {
                            selectedPerson = person
                            showForm = true
                        }
                        .buttonStyle(.bordered)

                        Button("Borrar") {
                            Task {
                                try? await viewModel.service.delete(id: person.id!)
                                await viewModel.load()
                            }
                        }
                        .buttonStyle(.borderedProminent)
                    }
                }
            }
            .navigationTitle("Personas")
            .toolbar {
                Button("Crear") {
                    selectedPerson = nil
                    showForm = true
                }
            }
            .task {
                await viewModel.load()
            }
            .sheet(isPresented: $showForm, onDismiss: {
                Task {
                    await viewModel.load()
                }
            }) {
                PersonFormView(person: selectedPerson)
            }
        }
    }
}
#Preview {
    PersonListView()
}
