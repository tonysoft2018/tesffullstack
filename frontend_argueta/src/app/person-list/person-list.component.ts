import { Component, OnInit } from '@angular/core';
import { PersonService } from '../services/person.service';
import { Router, RouterModule } from '@angular/router';
import { HttpClientModule, HttpErrorResponse } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule, DatePipe } from '@angular/common';
import { PageResponse } from '../model/page-response';
import { PersonResponse } from '../model/person-response';

@Component({
  selector: 'app-person-list',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule,
    DatePipe
  ],
  providers: [PersonService],
  templateUrl: './person-list.component.html',
  styleUrl: './person-list.component.css'
})
export class PersonListComponent implements OnInit {

  pageResponse: PageResponse<PersonResponse> = Object.assign({ data: [] });
  constructor(private service: PersonService,
    private router: Router,
  ) {

  }
  ngOnInit(): void {
    console.log('init');
    this.service.getPersonList({ page: 0, size: 100 }).subscribe({
      next: data => {
        if (data.status) {
          this.pageResponse = data.data;
        } else {


        }
      },
      error: (error: HttpErrorResponse) => {
        console.log(error);
      }
    });
  }

  public update(id: number): void {
    this.router.navigate(['person', id]);
  }
  public newPerson(): void {
    this.router.navigate(['person', 'nuevo']);
  }

  public delete(id: number): void {
    this.service.deletePerson(id).subscribe({
      next: res => {
        if (res.status && res.data) {
          this.ngOnInit();
        }
      }
    });
  }



}
