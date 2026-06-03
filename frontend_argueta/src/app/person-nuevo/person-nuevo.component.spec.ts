import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonNuevoComponent } from './person-nuevo.component';

describe('PersonNuevoComponent', () => {
  let component: PersonNuevoComponent;
  let fixture: ComponentFixture<PersonNuevoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PersonNuevoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PersonNuevoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
