import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponse } from '../models/api-response';
import { Person } from '../models/person';

@Injectable({ providedIn: 'root' })
export class PersonService {
  private readonly apiUrl = 'http://localhost:8080/api/persons';

  constructor(private readonly http: HttpClient) {}

  getAll(): Observable<ApiResponse<Person>> {
    return this.http.get<ApiResponse<Person>>(`${this.apiUrl}/findAll`);
  }

  getById(id: number): Observable<ApiResponse<Person>> {
    return this.http.get<ApiResponse<Person>>(`${this.apiUrl}/findById/${id}`);
  }

  create(person: Person): Observable<ApiResponse<Person>> {
    return this.http.post<ApiResponse<Person>>(`${this.apiUrl}/create`, person);
  }

  update(id: number, person: Person): Observable<ApiResponse<Person>> {
    return this.http.put<ApiResponse<Person>>(`${this.apiUrl}/update/${id}`, person);
  }

  delete(id: number): Observable<ApiResponse<Person>> {
    return this.http.delete<ApiResponse<Person>>(`${this.apiUrl}/delete/${id}`);
  }
}
