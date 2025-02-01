import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Persona } from '../models/persona.model';

@Injectable({
  providedIn: 'root',
})

export class ApiService {
  private apiUrl = 'http://localhost:8090/api'

  constructor(private http: HttpClient) {}

  getPersonas(): Observable<{ status: boolean; msg: string; data: Persona[] }> {
    return this.http.get<{ status: boolean; msg: string; data: Persona[] }>(`${this.apiUrl}/persons`);
  }

  agregarPersona(persona: Persona): Observable<{ status: boolean; msg: string; data: Persona }> {
    return this.http.post<{ status: boolean; msg: string; data: Persona }>(`${this.apiUrl}/persons`, persona);
  }

  eliminarPersona(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/persons/${id}`);
  }

  editarPersona(persona: Persona): Observable<{ status: boolean; msg: string; data: Persona }> {
    return this.http.put<{ status: boolean; msg: string; data: Persona }>(`${this.apiUrl}/persons/${persona.id}`, persona);
  }
}
