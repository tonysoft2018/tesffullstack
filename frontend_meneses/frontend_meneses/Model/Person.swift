//
//  Person.swift
//  frontend_meneses
//
//  Created by Américo MQ on 27/02/26.
//

import Foundation

struct Person: Identifiable, Codable {
    var id: Int?
    var firstName: String
    var lastName: String
    var birthDate: String
    var position: String
    var salary: Double

    enum CodingKeys: String, CodingKey {
        case id
        case firstName = "nombre"
        case lastName = "apellido"
        case birthDate = "fechaNacimiento"
        case position = "puesto"
        case salary = "sueldo"
    }
}
