import { ActivatedRouteSnapshot, RouterStateSnapshot, Router, Resolve } from '@angular/router';
import { Injectable } from '@angular/core';
import { PersonService } from '../services/person.service';

@Injectable({
  providedIn: 'root'
})
export class PersonResolver {

  constructor(
    private personService: PersonService
  ) {}

  public resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return this.personService.getPersonById(route.paramMap.get('id') as string);
  }

}