/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Starkev2TestModule } from '../../../test.module';
import { EntityPageFormatsMySuffixComponent } from 'app/entities/entity-page-formats-my-suffix/entity-page-formats-my-suffix.component';
import { EntityPageFormatsMySuffixService } from 'app/entities/entity-page-formats-my-suffix/entity-page-formats-my-suffix.service';
import { EntityPageFormatsMySuffix } from 'app/shared/model/entity-page-formats-my-suffix.model';

describe('Component Tests', () => {
    describe('EntityPageFormatsMySuffix Management Component', () => {
        let comp: EntityPageFormatsMySuffixComponent;
        let fixture: ComponentFixture<EntityPageFormatsMySuffixComponent>;
        let service: EntityPageFormatsMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Starkev2TestModule],
                declarations: [EntityPageFormatsMySuffixComponent],
                providers: []
            })
                .overrideTemplate(EntityPageFormatsMySuffixComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EntityPageFormatsMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EntityPageFormatsMySuffixService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new EntityPageFormatsMySuffix(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.entityPageFormats[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
