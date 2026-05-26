import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { PersonService } from '../../services/person.service';

@Component({
  selector: 'app-person-form',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatProgressSpinnerModule,
    MatSnackBarModule,
  ],
  templateUrl: './person-form.component.html',
  styleUrl: './person-form.component.scss',
})
export class PersonFormComponent implements OnInit {
  private readonly fb = inject(FormBuilder);
  private readonly personService = inject(PersonService);
  private readonly route = inject(ActivatedRoute);
  private readonly router = inject(Router);
  private readonly snackBar = inject(MatSnackBar);

  loading = false;
  saving = false;
  isEdit = false;
  personId?: number;

  form = this.fb.group({
    nombre: ['', [Validators.required, Validators.maxLength(255)]],
    apellido: ['', [Validators.required, Validators.maxLength(255)]],
    fechaNacimiento: ['', Validators.required],
    puesto: ['', [Validators.required, Validators.maxLength(255)]],
    sueldo: [null as number | null, [Validators.required, Validators.min(0)]],
  });

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      this.isEdit = true;
      this.personId = Number(idParam);
      this.loadPerson(this.personId);
    }
  }

  loadPerson(id: number): void {
    this.loading = true;
    this.personService.getById(id).subscribe({
      next: (response) => {
        this.loading = false;
        if (response.status && response.data?.length) {
          const person = response.data[0];
          this.form.patchValue({
            nombre: person.nombre,
            apellido: person.apellido,
            fechaNacimiento: person.fechaNacimiento,
            puesto: person.puesto,
            sueldo: person.sueldo,
          });
        } else {
          this.showMessage(response.msg, 'warn');
          this.router.navigate(['/']);
        }
      },
      error: () => {
        this.loading = false;
        this.showMessage('No se pudo cargar la persona.', 'error');
        this.router.navigate(['/']);
      },
    });
  }

  onSubmit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const value = this.form.getRawValue();
    const payload = {
      nombre: value.nombre!,
      apellido: value.apellido!,
      fechaNacimiento: value.fechaNacimiento!,
      puesto: value.puesto!,
      sueldo: Number(value.sueldo),
    };

    this.saving = true;
    const request$ = this.isEdit && this.personId !== undefined
      ? this.personService.update(this.personId, payload)
      : this.personService.create(payload);

    request$.subscribe({
      next: (response) => {
        this.saving = false;
        this.showMessage(response.msg, response.status ? 'success' : 'warn');
        if (response.status) {
          this.router.navigate(['/']);
        }
      },
      error: () => {
        this.saving = false;
        this.showMessage('Error al guardar el registro.', 'error');
      },
    });
  }

  onCancel(): void {
    this.router.navigate(['/']);
  }

  private showMessage(message: string, type: 'success' | 'warn' | 'error'): void {
    this.snackBar.open(message, 'Cerrar', {
      duration: 4000,
      panelClass: [`snack-${type}`],
      horizontalPosition: 'end',
      verticalPosition: 'top',
    });
  }
}
