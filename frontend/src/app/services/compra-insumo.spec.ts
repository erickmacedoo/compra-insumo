import { TestBed } from '@angular/core/testing';

import { CompraInsumoService } from './compra-insumo.service';

describe('CompraInsumoService', () => {
  let service: CompraInsumoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CompraInsumoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
