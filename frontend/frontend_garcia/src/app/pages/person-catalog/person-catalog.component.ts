import { Component, OnDestroy, OnInit } from '@angular/core';
import { Person } from '../../models/Person';
import { PersonService } from '../../services/person.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-person-catalog',
  templateUrl: './person-catalog.component.html',
  styleUrls: ['./person-catalog.component.css']
})
export class PersonCatalogComponent implements OnInit, OnDestroy {

  public displayedColumns: string[] = ['id', 'nombre', 'apellido', 'fechaNacimiento', "puesto", "sueldo"];
  public dataSource:Person[] = [];
  private subscription:Subscription = Subscription.EMPTY;

  constructor(
    private personService: PersonService
  ) { }

  public ngOnInit() {
    this.subscription = this.personService.getAllPersons()
    .subscribe(response=>{
      console.log(response);
      this.dataSource = response.data;
    });
  }

  public ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

}


