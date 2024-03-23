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

}

