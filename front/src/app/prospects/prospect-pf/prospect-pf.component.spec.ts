import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProspectPFComponent } from './prospect-pf.component';

describe('ProspectPFComponent', () => {
  let component: ProspectPFComponent;
  let fixture: ComponentFixture<ProspectPFComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProspectPFComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProspectPFComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
