import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonForm } from './person-form';

describe('PersonForm', () => {
  let component: PersonForm;
  let fixture: ComponentFixture<PersonForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PersonForm],
    }).compileComponents();

    fixture = TestBed.createComponent(PersonForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
