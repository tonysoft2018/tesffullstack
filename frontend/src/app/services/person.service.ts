import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class PersonService {
  private API_URL = 'http://localhost:8080/api/person';

  constructor(private http: HttpClient) { }

  getPeople(): Observable<any> {
    return this.http.get(this.API_URL);
  }

  getPersonById(id: number): Observable<any> {
    return this.http.get(`${this.API_URL}/${id}`);
  }

  createPerson(person: any): Observable<any> {
    return this.http.post(this.API_URL, person);
  }

  updatePerson(id: number, person: any): Observable<any> {
    return this.http.put(`${this.API_URL}/${id}`, person);
  }

  deletePerson(id: number): Observable<any> {
    return this.http.delete(`${this.API_URL}/${id}`);
  }
}