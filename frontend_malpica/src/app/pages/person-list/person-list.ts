import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PersonService } from '../../services/person';

@Component({
  selector: 'app-person-list',
  standalone: true,
  templateUrl: './person-list.html'
})
export class PersonListComponent implements OnInit {

  persons: any[] = [];

  constructor(
    private service: PersonService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadPersons();
  }

  loadPersons() {
    this.service.findAll().subscribe(resp => {
      this.persons = resp.data;
    });
  }

  create() {
    this.router.navigate(['/new']);
  }

  edit(id: number) {
    this.router.navigate(['/edit', id]);
  }

  delete(id: number) {

    if (!confirm('¿Desea eliminar el registro?')) {
      return;
    }

    this.service.delete(id)
      .subscribe(() => this.loadPersons());
  }
}
