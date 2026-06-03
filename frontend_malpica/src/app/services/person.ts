import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  private api = 'http://localhost:8080/api/persons';

  constructor(private http: HttpClient) {}

  findAll(): Observable<any> {
    return this.http.get(this.api);
  }

  findById(id: number): Observable<any> {
    return this.http.get(`${this.api}/${id}`);
  }

  create(person: any): Observable<any> {
    return this.http.post(this.api, person);
  }

  update(id: number, person: any): Observable<any> {
    return this.http.put(`${this.api}/${id}`, person);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.api}/${id}`);
  }
}
