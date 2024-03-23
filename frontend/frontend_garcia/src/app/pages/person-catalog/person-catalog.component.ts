import { Component, OnDestroy, OnInit } from '@angular/core';
import { Person } from '../../models/Person';
import { PersonService } from '../../services/person.service';
import { Subject, Subscription, filter, switchMap, tap } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { PersonDeleteComponent } from '../../dialogs/person-delete/person-delete.component';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-person-catalog',
  templateUrl: './person-catalog.component.html',
  styleUrls: ['./person-catalog.component.css']
})
export class PersonCatalogComponent implements OnInit, OnDestroy {

  public displayedColumns: string[] = ['id', 'nombre', 'apellido', 'fechaNacimiento', "puesto", "sueldo", "eliminar"];
  public dataSource:Person[] = [];
  private subscription:Subscription = Subscription.EMPTY;
  public isBusy: boolean = false;


  public getData = new Subject<void>();
  
  constructor(
    private personService: PersonService,
    public dialog: MatDialog,
    private snackBar: MatSnackBar
  ) { }

  public ngOnInit() {
    this.subscription = this.getData.pipe(
      switchMap(()=> this.personService.getAllPersons()),
    )
    .subscribe(response=>{
      this.dataSource = response.data;
    })
    ;

    this.getData.next();
  }

  public ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  public remove(person: Person){
    this.dialog.open(PersonDeleteComponent, {width: '450px', disableClose:true})
    .afterClosed()
    .pipe(
      filter(result => result),
      tap(()=> this.isBusy = true),
      switchMap(()=> this.personService.deletePerson(person)),
      tap(()=> this.getData.next())
    )
    .subscribe(response =>{
      this.snackBar.open(response.msg,"Ok",{ duration: 4000});
      this.isBusy = false;
    })
    ;
  }

}


