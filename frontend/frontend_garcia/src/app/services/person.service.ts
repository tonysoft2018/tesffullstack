import { Injectable } from '@angular/core';
import { Response } from '../models/Response';
import { Person } from '../models/Person';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PersonService {

constructor(
  private httpClient: HttpClient
) { }

public getAllPersons(){
  return this.httpClient.get<Response<Person>>("http://localhost:8080/api/persons");
}

public createPerson(request: Person){
  return this.httpClient.post<Response<Person>>("http://localhost:8080/api/persons",request);
}

public deletePerson(request: Person){
  return this.httpClient.delete<Response<Person>>(`http://localhost:8080/api/persons/${request.id}`);
}

public getPersonById(id: string){
  return this.httpClient.get<Response<Person>>(`http://localhost:8080/api/persons/${id}`);
}

public updatePerson(id: string, request: Person){
  console.log(id, request)
  return this.httpClient.put<Response<Person>>(`http://localhost:8080/api/persons/${id}`, request);
}

}

