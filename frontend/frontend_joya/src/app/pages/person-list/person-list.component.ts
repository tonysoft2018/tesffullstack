import { CurrencyPipe, DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { Router, RouterLink } from '@angular/router';
import { Person } from '../../models/person';
import { PersonService } from '../../services/person.service';
import { ConfirmDialogComponent } from '../../shared/confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-person-list',
  standalone: true,
  imports: [
    RouterLink,
    DatePipe,
    CurrencyPipe,
    MatToolbarModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatTableModule,
    MatProgressSpinnerModule,
    MatSnackBarModule,
    MatDialogModule,
  ],
  templateUrl: './person-list.component.html',
  styleUrl: './person-list.component.scss',
})
export class PersonListComponent implements OnInit {
  displayedColumns = ['nombre', 'apellido', 'fechaNacimiento', 'puesto', 'sueldo', 'acciones'];
  persons: Person[] = [];
  loading = true;

  constructor(
    private readonly personService: PersonService,
    private readonly snackBar: MatSnackBar,
    private readonly dialog: MatDialog,
    private readonly router: Router
  ) {}

  ngOnInit(): void {
    this.loadPersons();
  }

  loadPersons(): void {
    this.loading = true;
    this.personService.getAll().subscribe({
      next: (response) => {
        this.loading = false;
        if (response.status && response.data) {
          this.persons = response.data;
        } else {
          this.persons = [];
          this.showMessage(response.msg, 'warn');
        }
      },
      error: () => {
        this.loading = false;
        this.persons = [];
        this.showMessage('No se pudo conectar con el servidor. Verifique que el backend esté activo.', 'error');
      },
    });
  }

  onDelete(person: Person): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '400px',
      data: {
        title: 'Eliminar persona',
        message: `¿Desea eliminar a ${person.nombre} ${person.apellido}?`,
      },
    });

    dialogRef.afterClosed().subscribe((confirmed) => {
      if (!confirmed || person.id === undefined) {
        return;
      }
      this.personService.delete(person.id).subscribe({
        next: (response) => {
          this.showMessage(response.msg, response.status ? 'success' : 'warn');
          if (response.status) {
            this.loadPersons();
          }
        },
        error: () => this.showMessage('Error al eliminar el registro.', 'error'),
      });
    });
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
