import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProspectPJComponent } from './prospect-pj.component';

describe('ProspectPJComponent', () => {
  let component: ProspectPJComponent;
  let fixture: ComponentFixture<ProspectPJComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProspectPJComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProspectPJComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
