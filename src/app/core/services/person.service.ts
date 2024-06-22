import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiEndPoint } from '../constants/constants';
import { Response, Person } from '../interfaces/person.interface';

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  constructor(
    private http: HttpClient
  ) {}

  getAll(): Observable<Response<Person[]>> {
    return this.http.get<Response<Person[]>>(ApiEndPoint.Person.getAll);
  }

  get(id: number): Observable<Response<Person[]>> {
    return this.http.get<Response<Person[]>>(`${ApiEndPoint.Person.get}/${id}`);
  }

  create(data: Person): Observable<Response<Person[]>> {
    return this.http.post<Response<Person[]>>(ApiEndPoint.Person.add, data);
  }

  update(id: number, data: Person): Observable<Response<Person[]>> {
    return this.http.put<Response<Person[]>>(`${ApiEndPoint.Person.update}/${id}`, data);
  }

  delete(id: number): Observable<Response<Person[]>> {
    return this.http.delete<Response<Person[]>>(`${ApiEndPoint.Person.delete}/${id}`);
  }
}
