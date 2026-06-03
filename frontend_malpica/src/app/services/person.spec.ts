import { TestBed } from '@angular/core/testing';

import { Person } from './person';

describe('Person', () => {
  let service: Person;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Person);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
