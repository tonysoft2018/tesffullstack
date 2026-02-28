//
//  NetworkManager.swift
//  frontend_meneses
//
//  Created by Américo MQ on 27/02/26.
//

import Foundation

class NetworkManager {
    private let baseURL = "http://localhost:8080/person"

    func fetchAll() async throws -> [Person] {
        let url = URL(string: baseURL)!
        let (data, _) = try await URLSession.shared.data(from: url)

        let decoder = JSONDecoder()
        decoder.dateDecodingStrategy = .formatted(dateFormatter)

        let response = try decoder.decode([Person].self, from: data)
        return response
    }

    func delete(id: Int) async throws {
        let url = URL(string: "\(baseURL)/\(id)")!
        var request = URLRequest(url: url)
        request.httpMethod = "DELETE"
        _ = try await URLSession.shared.data(for: request)
    }

    func save(person: Person) async throws {

        let url: URL

        if let id = person.id {
            url = URL(string: "\(baseURL)/\(id)")!
        } else {
            url = URL(string: baseURL)!
        }

        var request = URLRequest(url: url)
        request.httpMethod = person.id == nil ? "POST" : "PUT"
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")

        request.httpBody = try JSONEncoder().encode(person)

        let (_, response) = try await URLSession.shared.data(for: request)

        guard let httpResponse = response as? HTTPURLResponse,
              200..<300 ~= httpResponse.statusCode else {
            throw URLError(.badServerResponse)
        }
    }

    private var dateFormatter: DateFormatter {
        let formatter = DateFormatter()
        formatter.dateFormat = "yyyy-MM-dd"
        formatter.locale = Locale(identifier: "en_US_POSIX")
        return formatter
    }
}
