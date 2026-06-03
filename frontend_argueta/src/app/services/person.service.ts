import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GenericResponse } from '../model/generic-response';
import { PageResponse } from '../model/page-response';
import { PersonResponse } from '../model/person-response';
import { environment } from '../../environments/environment';
import { PersonUpdateRequest } from '../model/person-update-request';
import { PersonCreateRequest } from '../model/person-create-request';

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  constructor(
    public http: HttpClient
  ) { }

  public getPersonList(page: { size: number, page: number }): Observable<GenericResponse<PageResponse<PersonResponse>>> {

    return this.http.get<GenericResponse<PageResponse<PersonResponse>>>(`${environment.url}/person?page=${page.page}&size=${page.size}`);
  }
  public getPersonById(id: number): Observable<GenericResponse<PersonResponse>> {

    return this.http.get<GenericResponse<PersonResponse>>(`${environment.url}/person/${id}`);
  }

  public updatePerson(person: PersonUpdateRequest): Observable<GenericResponse<PersonResponse>> {

    return this.http.put<GenericResponse<PersonResponse>>(`${environment.url}/person`, person);
  }
  public createPerson(person: PersonCreateRequest): Observable<GenericResponse<PersonResponse>> {

    return this.http.post<GenericResponse<PersonResponse>>(`${environment.url}/person`, person);
  }

  public deletePerson(id: number): Observable<GenericResponse<boolean>> {

    return this.http.delete<GenericResponse<boolean>>(`${environment.url}/person/${id}`);
  }
}
