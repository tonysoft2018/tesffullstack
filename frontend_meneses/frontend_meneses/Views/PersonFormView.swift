//
//  PersonFormView.swift
//  frontend_meneses
//
//  Created by Américo MQ on 27/02/26.
//

import SwiftUI

struct PersonFormView: View {

    @Environment(\.dismiss) var dismiss

    @State var person: Person?

    @State private var firstName = ""
    @State private var lastName = ""
    @State private var birthDate = Date()
    @State private var position = ""
    @State private var salary = ""

    private let service = NetworkManager()

    var body: some View {
        NavigationStack {
            Form {
                TextField("Nombre", text: $firstName)
                TextField("Apellido", text: $lastName)
                DatePicker("Fecha Nacimiento",
                           selection: $birthDate,
                           displayedComponents: .date)
                TextField("Puesto", text: $position)
                TextField("Sueldo", text: $salary)
                    .keyboardType(.decimalPad)
            }
            .navigationTitle(person == nil ? "Crear Persona" : "Editar Persona")
            .toolbar {
                ToolbarItem(placement: .confirmationAction) {
                    Button("Guardar") {
                        Task {
                            await save()
                        }
                    }
                }

                ToolbarItem(placement: .cancellationAction) {
                    Button("Cancelar") {
                        dismiss()
                    }
                }
            }
            .onAppear {
                if let person = person {
                    firstName = person.firstName
                    lastName = person.lastName
                    let formatter = DateFormatter()
                    formatter.dateFormat = "yyyy-MM-dd"
                    formatter.locale = Locale(identifier: "en_US_POSIX")
                    if let date = formatter.date(from: person.birthDate) {
                        birthDate = date
                    }
                    position = person.position
                    salary = String(person.salary)
                }
            }
        }
    }

    func save() async {
        let formatter = DateFormatter()
        formatter.dateFormat = "yyyy-MM-dd"
        formatter.locale = Locale(identifier: "en_US_POSIX")

        let fechaString = formatter.string(from: birthDate)

        let newPerson = Person(
            id: person?.id,
            firstName: firstName,
            lastName: lastName,
            birthDate: fechaString,
            position: position,
            salary: Double(salary) ?? 0
        )

        try? await service.save(person: newPerson)
        dismiss()
    }
}

#Preview {
    PersonFormView()
}
