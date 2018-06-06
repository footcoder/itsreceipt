import { TestBed, inject } from '@angular/core/testing';

import { ReceiptsService } from './receipts.service';

describe('ReceiptsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ReceiptsService]
    });
  });

  it('should be created', inject([ReceiptsService], (service: ReceiptsService) => {
    expect(service).toBeTruthy();
  }));
});
