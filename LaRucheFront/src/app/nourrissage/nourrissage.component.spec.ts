import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NourrissageComponent } from './nourrissage.component';

describe('NourrissageComponent', () => {
  let component: NourrissageComponent;
  let fixture: ComponentFixture<NourrissageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NourrissageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NourrissageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
