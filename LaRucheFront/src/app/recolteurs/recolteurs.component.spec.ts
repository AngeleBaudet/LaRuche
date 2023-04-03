import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecolteursComponent } from './recolteurs.component';

describe('RecolteursComponent', () => {
  let component: RecolteursComponent;
  let fixture: ComponentFixture<RecolteursComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecolteursComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecolteursComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
