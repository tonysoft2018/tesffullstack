import { Component, OnInit } from '@angular/core';
import {
 FormBuilder,
 FormGroup,
 ReactiveFormsModule
} from '@angular/forms';

import {
 ActivatedRoute,
 Router
} from '@angular/router';

import { PersonService } from '../../services/person';

@Component({
  selector: 'app-person-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './person-form.html'
})
export class PersonFormComponent implements OnInit {

  form!: FormGroup;

  id?: number;

  constructor(
    private fb: FormBuilder,
    private service: PersonService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {

    this.form = this.fb.group({
      nombre: [''],
      apellido: [''],
      fechaNacimiento: [''],
      puesto: [''],
      sueldo: [0]
    });

    const paramId =
      this.route.snapshot.paramMap.get('id');

    if (paramId) {

      this.id = Number(paramId);

      this.service.findById(this.id)
        .subscribe(resp => {

          this.form.patchValue(resp.data);

        });
    }
  }

  save() {

    if (this.id) {

      this.service
        .update(this.id, this.form.value)
        .subscribe(() => this.router.navigate(['/']));
    } else {

      this.service
        .create(this.form.value)
        .subscribe(() => this.router.navigate(['/']));
    }
  }

  cancel() {

    this.router.navigate(['/']);
  }
}
